import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-advertiser',
  templateUrl: './advertiser.component.html',
  styleUrls: ['./advertiser.component.css']
})
export class AdvertiserComponent implements OnInit {
  name:string;

  constructor() { }

  ngOnInit() {
    this.name = "iluha";
  }

}
