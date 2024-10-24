/**
 * This software was developed and / or modified by Raytheon Company,
 * pursuant to Contract DG133W-05-CQ-1067 with the US Government.
 * 
 * U.S. EXPORT CONTROLLED TECHNICAL DATA
 * This software product contains export-restricted data whose
 * export/transfer/disclosure is restricted by U.S. law. Dissemination
 * to non-U.S. persons whether in the United States or abroad requires
 * an export license or other authorization.
 * 
 * Contractor Name:        Raytheon Company
 * Contractor Address:     6825 Pine Street, Suite 340
 *                         Mail Stop B8
 *                         Omaha, NE 68106
 *                         402.291.0100
 * 
 * See the AWIPS II Master Rights File ("Master Rights File.pdf") for
 * further licensing information.
 **/
\set ON_ERROR_STOP 1
\connect metadata;

-- Start a transaction
BEGIN;

-- Update SiteSubscription value entries to not have the notify attribute
update ebxml.value set stringvalue = regexp_replace(stringvalue, 'notify=".*?" ', '', 'g');

-- Update GroupDefinition value entries to not have the option attribute
-- Explanation of how this works: \\1 is the first set of parentheses, \\2 is the second set,
-- So we are removing the option="<anything>" section out of any rows that have it and stitching the
-- data back together. 
update ebxml.value set stringvalue = regexp_replace(stringvalue, '(<groupDefinition.*?)option=".*?"(.*)', E'\\1\\2', 'g');

-- Commit the transaction
END;
