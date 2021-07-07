import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LogoutService {
  constructor(private httpClient: HttpClient) {}

  public logout(): Observable<string> {
    let theHeaders = new HttpHeaders().set(
      'token',
      localStorage.getItem('token')!
    );
    let options = { headers: theHeaders };

    return this.httpClient.post<string>(
      'http://localhost:8080/logout',
      null,
      options
    );
  }
}
