import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ClientType } from '../models/client-type.enum';
import { LoginItem } from '../models/login-item.model';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  constructor(private httpClient: HttpClient) {}

  public login(
    email: string,
    password: string,
    clientType: ClientType
  ): Observable<LoginItem> {
    console.log(email);
    console.log(password);
    console.log(clientType);
    return this.httpClient.post<LoginItem>(
      'http://localhost:8080/login?clientType=' +
        clientType +
        '&email=' +
        email +
        '&password=' +
        password,
      { responseType: 'text' }
    );
  }
}
