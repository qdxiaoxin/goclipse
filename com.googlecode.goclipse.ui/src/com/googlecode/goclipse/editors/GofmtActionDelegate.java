package com.googlecode.goclipse.editors;

import melnorme.lang.ide.core.utils.process.ExternalProcessEclipseHelper;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.preference.IPreferenceStore;

import com.googlecode.goclipse.Activator;
import com.googlecode.goclipse.builder.GoToolManager;
import com.googlecode.goclipse.preferences.PreferenceConstants;

/**
 * 
 * @author steel
 *
 */
public class GofmtActionDelegate extends TransformTextAction {
	
    public GofmtActionDelegate() {
    	super("gofmt");
	}

	@Override
	protected String transformText(final String text) throws CoreException {
		final String currentContent = text;
		
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		String gofmtPath = preferenceStore.getString(PreferenceConstants.FORMATTER_PATH);
		
		IProject project = null; // TODO
		IProgressMonitor pm = new NullProgressMonitor(); // TODO
		
		ExternalProcessEclipseHelper processHelper = GoToolManager.getDefault().
				runGoTool(gofmtPath, project, pm, currentContent);
		
		String formattedText = processHelper.getStdOutBytes_CoreException().toString();
		
		if (!formattedText.equals(currentContent)) {
			return formattedText;
		} else {
			return null;
		}
	}

}