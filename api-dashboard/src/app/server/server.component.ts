import { Component, OnInit } from '@angular/core';
import { DatabaseService } from '../services/database.service';


@Component({
  selector: 'app-server',
  templateUrl: './server.component.html',
  styleUrls: ['./server.component.css']
})
export class ServerComponent implements OnInit {

  constructor(
    private db_service: DatabaseService,
  ) { }

  get serverStatusCode() { return this.db_service.status }
  isServerUp = true; 

  ngOnInit(): void {
    this.checkServer()
    if (this.serverStatusCode >= 400 ) {
      this.isServerUp = false;
    }
  }

  checkServer() {
    this.db_service.health_check();
  }

}
