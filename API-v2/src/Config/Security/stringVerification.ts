import CryptoJS from "crypto-js"

export default class StringVerification {

  /**
   *  [check if input string is only made of digits]
   *  @param  input   [the string to check]
   *  @return         [true if the string is only made of digits]
   */
  public verifyIdRequest(input: string):boolean {
    const regex = /^\d+$/
    return regex.test(input)
  }

  /**
   *  [encrypt a password passed as input]
   *  @param  input   [the password to encrypt]
   *  @return         [the encrypted password]
   */
  public encryptPassword(input: string):string {
    return CryptoJS.SHA3(input).toString(CryptoJS.enc.Base64)
  }
}
