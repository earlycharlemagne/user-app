import { Component } from '@angular/core';
import {User} from "./user/user.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient, HttpParams, HttpHeaders} from "@angular/common/http";
import {map} from "rxjs/operators";
import {environment} from "../environments/environment";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  searchForm: FormGroup;
  user: User;
  isRetrievingUser: boolean = false;
  error: string;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.searchForm = new FormGroup({
      'firstname': new FormControl(null, Validators.required),
      'surname': new FormControl(null, Validators.required)
    })
  }

  onSearch() {
    this.getUser(this.searchForm.get('firstname').value, this.searchForm.get('surname').value);
  }

  private getUser(firstname: string, surname: string) {
    this.error = '';
    this.isRetrievingUser = true;

    this.http.get<User>(
      `${environment.userApiUrl}/api/v1/users`,
      {
        headers: new HttpHeaders({
          'Authorization': `Basic ${environment.authenticationKey}`
        }),
        params: new HttpParams().set('firstname', firstname)
                                .set('surname', surname)
      })
      .pipe(map(response => {
        const user: User = new User('','','','','');

        // set firstname
        user['firstname'] = response.hasOwnProperty('firstname') ? response['firstname'] : '';
        // set lastname
        user['surname'] = response.hasOwnProperty('surname') ? response['surname'] : '';
        // set date of birth
        user['dateOfBirth'] = response.hasOwnProperty('dateOfBirth') ? response['dateOfBirth']: '';
        // set gender
        user['gender'] = response.hasOwnProperty('gender') ? response['gender']: '';
        // set image url
        user['imageUrl'] = response.hasOwnProperty('picture') ? response['picture']['large']: '';

        return user;
      }))
      .subscribe(user => {
        this.user = user;
        this.isRetrievingUser = false;
      }, error => {
        this.error = error.message;
        this.isRetrievingUser = false;
      })
  }
}
