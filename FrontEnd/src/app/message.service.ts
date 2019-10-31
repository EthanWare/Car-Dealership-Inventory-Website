import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  message: string;

  set(message: string){
    this.message = message;

    setTimeout(()=>this.clear(), 5000);
  }

  clear(){
    this.message = "";
  }
}
