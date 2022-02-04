/**
 * generated by Xtext 2.25.0
 */
package org.eclipse.fordiac.ide.structuredtextalgorithm.sTAlgorithm;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.fordiac.ide.structuredtextcore.sTCore.STStatement;
import org.eclipse.fordiac.ide.structuredtextcore.sTCore.VarDeclarationBlock;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Body</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.fordiac.ide.structuredtextalgorithm.sTAlgorithm.STAlgorithmBody#getVarTempDeclarations <em>Var Temp Declarations</em>}</li>
 *   <li>{@link org.eclipse.fordiac.ide.structuredtextalgorithm.sTAlgorithm.STAlgorithmBody#getStatements <em>Statements</em>}</li>
 * </ul>
 *
 * @see org.eclipse.fordiac.ide.structuredtextalgorithm.sTAlgorithm.STAlgorithmPackage#getSTAlgorithmBody()
 * @model
 * @generated
 */
public interface STAlgorithmBody extends EObject
{
  /**
   * Returns the value of the '<em><b>Var Temp Declarations</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.fordiac.ide.structuredtextcore.sTCore.VarDeclarationBlock}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Var Temp Declarations</em>' containment reference list.
   * @see org.eclipse.fordiac.ide.structuredtextalgorithm.sTAlgorithm.STAlgorithmPackage#getSTAlgorithmBody_VarTempDeclarations()
   * @model containment="true"
   * @generated
   */
  EList<VarDeclarationBlock> getVarTempDeclarations();

  /**
   * Returns the value of the '<em><b>Statements</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.fordiac.ide.structuredtextcore.sTCore.STStatement}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Statements</em>' containment reference list.
   * @see org.eclipse.fordiac.ide.structuredtextalgorithm.sTAlgorithm.STAlgorithmPackage#getSTAlgorithmBody_Statements()
   * @model containment="true"
   * @generated
   */
  EList<STStatement> getStatements();

} // STAlgorithmBody
