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
package com.raytheon.viz.mpe.ui.actions;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.raytheon.viz.mpe.ui.RunDQCPreProcessorDialog;

/**
 * Displays the Run DQC PreProcessor Configuration dialog when a user clicks on
 * the "Run DQC PreProcessor..." menu option.
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Apr 5, 2018  7184       bkowal      Initial creation
 *
 * </pre>
 *
 * @author bkowal
 */

public class RunDQCPreProcessorHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                .getShell();
        RunDQCPreProcessorDialog runDQCPreProcessorDialog = new RunDQCPreProcessorDialog(
                shell);
        runDQCPreProcessorDialog.open();
        return null;
    }
}