/**
 * generated by Xtext 2.20.0
 */
package org.eclipse.fordiac.ide.model.structuredtext.structuredText;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>String Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.fordiac.ide.model.structuredtext.structuredText.StringLiteral#getLength <em>Length</em>}</li>
 *   <li>{@link org.eclipse.fordiac.ide.model.structuredtext.structuredText.StringLiteral#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.eclipse.fordiac.ide.model.structuredtext.structuredText.StructuredTextPackage#getStringLiteral()
 * @model
 * @generated
 */
public interface StringLiteral extends Constant
{
  /**
   * Returns the value of the '<em><b>Length</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Length</em>' attribute.
   * @see #setLength(long)
   * @see org.eclipse.fordiac.ide.model.structuredtext.structuredText.StructuredTextPackage#getStringLiteral_Length()
   * @model
   * @generated
   */
  long getLength();

  /**
   * Sets the value of the '{@link org.eclipse.fordiac.ide.model.structuredtext.structuredText.StringLiteral#getLength <em>Length</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Length</em>' attribute.
   * @see #getLength()
   * @generated
   */
  void setLength(long value);

  /**
   * Returns the value of the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' attribute.
   * @see #setValue(String)
   * @see org.eclipse.fordiac.ide.model.structuredtext.structuredText.StructuredTextPackage#getStringLiteral_Value()
   * @model
   * @generated
   */
  String getValue();

  /**
   * Sets the value of the '{@link org.eclipse.fordiac.ide.model.structuredtext.structuredText.StringLiteral#getValue <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' attribute.
   * @see #getValue()
   * @generated
   */
  void setValue(String value);

} // StringLiteral
