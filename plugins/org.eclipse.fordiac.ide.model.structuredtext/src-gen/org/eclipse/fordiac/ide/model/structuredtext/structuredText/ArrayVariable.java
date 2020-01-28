/**
 * generated by Xtext 2.20.0
 */
package org.eclipse.fordiac.ide.model.structuredtext.structuredText;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Array Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.fordiac.ide.model.structuredtext.structuredText.ArrayVariable#getArray <em>Array</em>}</li>
 *   <li>{@link org.eclipse.fordiac.ide.model.structuredtext.structuredText.ArrayVariable#getIndex <em>Index</em>}</li>
 * </ul>
 *
 * @see org.eclipse.fordiac.ide.model.structuredtext.structuredText.StructuredTextPackage#getArrayVariable()
 * @model
 * @generated
 */
public interface ArrayVariable extends Variable
{
  /**
   * Returns the value of the '<em><b>Array</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Array</em>' containment reference.
   * @see #setArray(Variable)
   * @see org.eclipse.fordiac.ide.model.structuredtext.structuredText.StructuredTextPackage#getArrayVariable_Array()
   * @model containment="true"
   * @generated
   */
  Variable getArray();

  /**
   * Sets the value of the '{@link org.eclipse.fordiac.ide.model.structuredtext.structuredText.ArrayVariable#getArray <em>Array</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Array</em>' containment reference.
   * @see #getArray()
   * @generated
   */
  void setArray(Variable value);

  /**
   * Returns the value of the '<em><b>Index</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.fordiac.ide.model.structuredtext.structuredText.Expression}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Index</em>' containment reference list.
   * @see org.eclipse.fordiac.ide.model.structuredtext.structuredText.StructuredTextPackage#getArrayVariable_Index()
   * @model containment="true"
   * @generated
   */
  EList<Expression> getIndex();

} // ArrayVariable
