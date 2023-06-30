/*******************************************************************************
 * Copyright (c) 2022 Primetals Technologies Austria GmbH
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Hesam Rezaee
 *       - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.fordiac.ide.globalconstantseditor;

import org.eclipse.fordiac.ide.globalconstantseditor.resource.GlobalConstantsResource;
import org.eclipse.fordiac.ide.structuredtextcore.converter.STCoreValueConverters;
import org.eclipse.fordiac.ide.structuredtextcore.naming.STCoreQualifiedNameProvider;
import org.eclipse.fordiac.ide.structuredtextcore.parsetree.reconstr.STCoreCommentAssociater;
import org.eclipse.fordiac.ide.structuredtextcore.scoping.STCoreLinkingDiagnosticMessageProvider;
import org.eclipse.xtext.Constants;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.linking.ILinkingDiagnosticMessageProvider;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.parsetree.reconstr.ICommentAssociater;
import org.eclipse.xtext.resource.XtextResource;

import com.google.inject.Binder;
import com.google.inject.name.Names;

/** Use this class to register components to be used at runtime / without the Equinox extension registry. */
@SuppressWarnings("static-method")
public class GlobalConstantsRuntimeModule extends AbstractGlobalConstantsRuntimeModule {

	@Override
	public Class<? extends IValueConverterService> bindIValueConverterService() {
		return STCoreValueConverters.class;
	}

	@Override
	public Class<? extends XtextResource> bindXtextResource() {
		return GlobalConstantsResource.class;
	}

	@Override
	public void configureFileExtensions(final Binder binder) {
		if (properties == null || properties.getProperty(Constants.FILE_EXTENSIONS) == null) {
			binder.bind(String.class).annotatedWith(Names.named(Constants.FILE_EXTENSIONS))
					.toInstance("globalconsts,gcf"); //$NON-NLS-1$
		}
	}

	@Override
	public Class<? extends IQualifiedNameProvider> bindIQualifiedNameProvider() {
		return STCoreQualifiedNameProvider.class;
	}

	public Class<? extends ICommentAssociater> bindICommentAssociater() {
		return STCoreCommentAssociater.class;
	}

	public Class<? extends ILinkingDiagnosticMessageProvider> bindILinkingDiagnosticMessageProvider() {
		return STCoreLinkingDiagnosticMessageProvider.class;
	}
}
