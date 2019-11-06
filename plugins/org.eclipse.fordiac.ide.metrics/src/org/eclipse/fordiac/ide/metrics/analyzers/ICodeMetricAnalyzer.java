/*******************************************************************************
 * Copyright (c) 2019 TU Wien, ACIN, Johannes Kepler University Linz
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Peter Gsellmann - initial API and implementation and/or initial documentation
 *   Alois Zoitl - Changed analysis result to key value pairs
 *******************************************************************************/
package org.eclipse.fordiac.ide.metrics.analyzers;

import java.util.List;

import org.eclipse.fordiac.ide.model.libraryElement.Application;

public interface ICodeMetricAnalyzer {

	void calculateMetrics(Application app);

	List<MetricData> getResults();

}
