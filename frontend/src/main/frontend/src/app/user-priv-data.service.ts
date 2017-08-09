import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { UserData } from './user-data'

@Injectable()
export class UserPrivDataService {

  private userPrivDataUrl = '/api/userPrivData';
  private deleteUserPrivDataUrl = '/api/delete';
  private headers = new Headers({ 'Content-Type': 'application/json' });

  constructor(private http: Http) { }

  getAllUserPrivData(): Promise<UserData[]> {
    return this.http.get(this.userPrivDataUrl)
      .toPromise()
      .then(response => response.json().object as UserData[])
      .catch(this.handleError);
  }

  addUserPrivData(userPrivData: UserData): Promise<UserData> {
    return this.http
      .post(this.userPrivDataUrl + "/save", JSON.stringify(userPrivData), {headers: this.headers})
      .toPromise()
      .then(res => res.json().object as UserData)
      .catch(this.handleError);
  }

  deleteUserPrivData(userPrivData: UserData): Promise<void> {
    const url = `/api/userPrivData/delete/${userPrivData.id}`;
    return this.http
      .get(url, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error("Error", error);
    return Promise.reject(error.message || error);
  }
}
