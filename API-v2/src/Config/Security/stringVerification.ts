export default class stringVerification {
  /**
   * verifyIdRequest = check if id string is only made of digits.
   */
  public verifyIdRequest(input: string) {
    const reg = new RegExp("/^[0-9]*$/")
    if (reg.test(input)) {
      return true
    } else {
      return false
    }
  }
}
