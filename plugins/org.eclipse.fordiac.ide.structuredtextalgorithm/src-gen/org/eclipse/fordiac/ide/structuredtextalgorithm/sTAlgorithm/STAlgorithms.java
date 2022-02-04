/**
 * generated by Xtext 2.25.0
 */
package org.eclipse.fordiac.ide.structuredtextalgorithm.sTAlgorithm;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ST Algorithms</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.fordiac.ide.structuredtextalgorithm.sTAlgorithm.STAlgorithms#getAlgorithms <em>Algorithms</em>}</li>
 * </ul>
 *
 * @see org.eclipse.fordiac.ide.structuredtextalgorithm.sTAlgorithm.STAlgorithmPackage#getSTAlgorithms()
 * @model
 * @generated
 */
public interface STAlgorithms extends EObject
{
  /**
   * Returns the value of the '<em><b>Algorithms</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.fordiac.ide.structuredtextalgorithm.sTAlgorithm.STAlgorithm}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Algorithms</em>' containment reference list.
   * @see org.eclipse.fordiac.ide.structuredtextalgorithm.sTAlgorithm.STAlgorithmPackage#getSTAlgorithms_Algorithms()
   * @model containment="true"
   * @generated
   */
  EList<STAlgorithm> getAlgorithms();

} // STAlgorithms
