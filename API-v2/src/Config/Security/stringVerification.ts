export default class StringVerification {

  /**
   *  [check if input string is only made of digits]
   *  @param  input   [the string to check]
   *  @return         [true if the string is only made of digits]
   */
  public verifyIdRequest(input: string) {
    const regex = /^\d+$/
    return regex.test(input)
  }
}
