##
# This software was developed and / or modified by Raytheon Company,
# pursuant to Contract DG133W-05-CQ-1067 with the US Government.
# 
# U.S. EXPORT CONTROLLED TECHNICAL DATA
# This software product contains export-restricted data whose
# export/transfer/disclosure is restricted by U.S. law. Dissemination
# to non-U.S. persons whether in the United States or abroad requires
# an export license or other authorization.
# 
# Contractor Name:        Raytheon Company
# Contractor Address:     6825 Pine Street, Suite 340
#                         Mail Stop B8
#                         Omaha, NE 68106
#                         402.291.0100
# 
# See the AWIPS II Master Rights File ("Master Rights File.pdf") for
# further licensing information.
##
#
# Request TCV Advisory Files
#
#    
#     SOFTWARE HISTORY
#    
#    Date            Ticket#       Engineer       Description
#    ------------    ----------    -----------    --------------------------
#    12/05/14        4953          randerso       Initial Creation.
#    03/10/2015      #4129         randerso       Refactored server selection code into a reusable method
# 
##

##
# This is a base file that is not intended to be overridden.
##



import os, errno, tempfile
from xml.etree import ElementTree

import IrtAccess
import VTECPartners

logger = None

def init_logging():
    import iscUtil
    import logging
    global logger
    logger = iscUtil.getLogger("requestTCV", logLevel=logging.INFO)

def runFromJava(siteID, config):
    import siteConfig
    host      = str(config.getServerHost())
    port      = str(config.getRpcPort())
    protocol  = str(config.getProtocolVersion())
    mhsid     = str(config.getMhsid())
    ancf      = str(config.iscRoutingTableAddress().get("ANCF"))
    bncf      = str(config.iscRoutingTableAddress().get("BNCF"))
    xmtScript = str(config.transmitScript())

    init_logging()
    
    tempdir = os.path.join(siteConfig.GFESUITE_HOME, "products", "TCV")
    try:
        os.makedirs(tempdir, 0o755)
    except OSError as e:
        if e.errno != errno.EEXIST:
            logger.warn("%s: '%s'" % (e.strerror,e.filename))

    sourceServer = {'mhsid'   : mhsid, 
                    'host'    : host, 
                    'port'    : port,
                    'protocol': protocol, 
                    'site'    : siteID}
    try:
        destSites = VTECPartners.VTEC_TABLE_REQUEST_SITES
        if not destSites:
            raise Exception('No destSites defined for VTEC_TABLE_REQUEST_SITES')
    
        irt = IrtAccess.IrtAccess(ancf, bncf, logger=logger)
        msgSendDest, xml = irt.createDestinationXML(destSites, sourceServer)
        
        # create the XML file
        with tempfile.NamedTemporaryFile(suffix='.xml', dir=tempdir, delete=False) as fd:
            fnameXML = fd.name
            fd.write(ElementTree.tostring(xml, encoding="utf-8"))    
    
        # don't send to ourselves
        if mhsid in msgSendDest:
            msgSendDest.remove(mhsid)
            
        if len(msgSendDest) > 0:
            # Now send the message
            irt.transmitFiles("GET_TCV_FILES", msgSendDest, mhsid, [fnameXML], xmtScript)
    except:
        logger.exception('Error requesting TCV files for site: ' + siteID)
