import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-device-owner',
  templateUrl: './device-owner.component.html',
  styleUrls: ['./device-owner.component.css']
})
export class DeviceOwnerComponent implements OnInit {
  name:string;
  devices:string[];

  isView:boolean = false;

  constructor() { }

  ngOnInit() {
    this.name = "darova";
    this.devices = ['Xiaomi','Screen_1'];
  }

  addDevice(device) {
    this.devices.push(device);
    return false;
  }

  deleteDevice(device) {
    for(let i = 0; i < this.devices.length; i++) {
      if(device == this.devices[i]) {
        this.devices.splice(i, 1);
        break;
      }
    }
  }

  viewScreen(device) {
    this.isView = !this.isView;
  }

}
