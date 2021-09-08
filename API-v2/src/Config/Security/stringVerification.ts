import CryptoJS from "crypto-js"

export default class StringVerification {

  /**
   *  [check if input string is only made of digits and positive]
   *  @param  input   [the string to check]
   *  @return         [true if the string is only made of digits and positive]
   */
  public verifyIdRequest(input: string):boolean {
    const regex = /^\d+$/
    if (regex.test(input)) 
    {
      const number = Number(input)
      if (number > 0) 
      {
        return true
      }
    }
    return false
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
