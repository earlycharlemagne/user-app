export class User {

  public firstname: string;
  public surname: string;
  public gender: string;
  public dateOfBirth: string;
  public imageUrl: string;

  constructor(firstname: string, surname: string, gender: string, dateOfBirth: string, imageUrl: string) {
    this.firstname = firstname;
    this.surname = surname;
    this.gender = gender;
    this.dateOfBirth = dateOfBirth;
    this.imageUrl = imageUrl;
  }
}
