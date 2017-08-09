import {Injectable} from '@angular/core';
import {AES} from 'crypto-js';
import * as crypto from 'crypto-js';
import {UserData} from './user-data';


@Injectable()
export class CryptoService {


  constructor(private userData: UserData) {
  }

  userPrivData: UserData[] = [];
  decryptedUserPrivData: UserData[] = [];
  encryptedUserPrivData: UserData;

  encrypt(userData: UserData, key: String): UserData {

    userData.usernameOrEmail = AES.encrypt(userData.usernameOrEmail.toString(), key).toString();
    userData.password = AES.encrypt(userData.password, key).toString();
    userData.site = AES.encrypt(userData.site, key).toString();
    userData.description = AES.encrypt(userData.description, key).toString();

    this.encryptedUserPrivData = userData;

    return this.encryptedUserPrivData;

  }

  encryptPrivateKey(privateKey: String, key: String): String {
    privateKey = AES.encrypt(privateKey, key).toString();
    return privateKey;
  }

  decryptPrivateKey(privateKey: String, key: String): String {
    privateKey = AES.decrypt(privateKey, key).toString(crypto.enc.Utf8);
    return privateKey;
  }

  decrypt(userPrivData: UserData[], key: String): UserData[] {
    this.decryptedUserPrivData = [];

    userPrivData.forEach((singleUserPrivData) => {
        this.decryptedUserPrivData.push(this.decryptSingle(singleUserPrivData, key))
      }
    );
    return this.decryptedUserPrivData;

  }

  decryptSingle(userPrivData: UserData, key: String): UserData {
    userPrivData.usernameOrEmail = AES.decrypt(userPrivData.usernameOrEmail, key).toString(crypto.enc.Utf8);
    userPrivData.password = AES.decrypt(userPrivData.password, key).toString(crypto.enc.Utf8);
    userPrivData.site = AES.decrypt(userPrivData.site, key).toString(crypto.enc.Utf8);
    userPrivData.description = AES.decrypt(userPrivData.description, key).toString(crypto.enc.Utf8);

    return userPrivData;
  }
}
