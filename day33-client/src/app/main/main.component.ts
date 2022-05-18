import { Component, OnInit } from '@angular/core';
import { POTotal } from '../inventory-model';
import { InventoryService } from '../inventory.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  potArr: POTotal[] = [];

  constructor(private invSvc: InventoryService) { }

  ngOnInit(): void {
    this.invSvc.getPOTotal()
      .then(result => this.potArr = result)
      .catch(error => alert(`Error: ${JSON.stringify(error)}`));
  }

}
