export default class StringVerification {
  /**
   * verifyIdRequest = check if id string is only made of digits.
   */
  public verifyIdRequest(input: string) {
    const regex = /^\d+$/
    return regex.test(input)
  }
}
