export class AuthService {

  isAuth = false;

  signIn() {
    return new Promise((resolve, reject) => {
      try {
        setTimeout( () => {
          this.isAuth = true;
          resolve(true);
        }, 2000);
      } catch (error) {
        reject(error)
      }
    });
  }

  signOut() {
    this.isAuth = false;
  }
}