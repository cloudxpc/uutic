import { Component, OnInit } from '@angular/core';
import {ToolbarService} from "./toolbar.service";

import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent implements OnInit {

  constructor(private service: ToolbarService) { }

  ngOnInit() {
  }

  test(){
    this.service.test().subscribe(value => console.log(value));
  }
}
