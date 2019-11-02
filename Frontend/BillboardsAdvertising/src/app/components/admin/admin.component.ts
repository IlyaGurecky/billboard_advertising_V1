import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  name:string
  users:string[]
  devices:string[]

  isView:boolean = false;
  isViewMonitor:boolean = false;

  constructor() { }

  ngOnInit() {
    this.name='Serega';
    this.users=["Stas", "Nagibator2009", "Mishanya", "Sanek"];
    this.devices=["Xiaomi", "Phone", "Mobila"];
  }

  addUser(user) {
    this.users.push(user);
    return false;
  }

  deleteUser(user) {
    for(let i = 0; i < this.users.length; i++) {
      if(user == this.users[i]) {
        this.users.splice(i, 1);
        break;
      }
    }
  }

  viewDevices() {
        this.isView = !this.isView;
  }

  viewMonitor() {
        this.isViewMonitor = !this.isViewMonitor;
  }


}
