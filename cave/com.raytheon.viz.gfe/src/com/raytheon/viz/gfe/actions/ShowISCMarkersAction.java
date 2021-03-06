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

package com.raytheon.viz.gfe.actions;

import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.menus.UIElement;

import com.raytheon.viz.gfe.core.DataManager;

/**
 * Action to toggle the display of the ISC Markers
 * 
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#     Engineer    Description
 * ------------ ----------  ----------- --------------------------
 * 07/20/09      1995       bphillip    Initial release
 * 
 * </pre>
 * 
 * @author bphillip
 * @version 1
 */
public class ShowISCMarkersAction extends AbstractHandler implements
        IElementUpdater {

    public static final String COMMAND_ID = "com.raytheon.viz.gfe.actions.showISCMarkers";

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        DataManager dataMgr = DataManager.getCurrentInstance();
        if (dataMgr == null) {
            return null;
        }

        dataMgr.getSpatialDisplayManager().setShowISCMarkers(
                !dataMgr.getSpatialDisplayManager().isShowISCMarkers());

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.commands.IElementUpdater#updateElement(org.eclipse.ui.
     * menus.UIElement, java.util.Map)
     */
    @Override
    public void updateElement(UIElement element,
            @SuppressWarnings("rawtypes") Map parameters) {
        DataManager dm = DataManager.getCurrentInstance();
        if (dm == null) {
            return;
        }
        element.setChecked(dm.getSpatialDisplayManager().isShowISCMarkers());
    }
}
