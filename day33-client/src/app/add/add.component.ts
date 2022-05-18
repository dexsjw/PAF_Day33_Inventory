import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators  } from '@angular/forms';
import { Router } from '@angular/router';

import { PO } from '../inventory-model';
import { InventoryService } from '../inventory.service';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  poForm!: FormGroup;
  liArr!: FormArray;

  constructor(private fb: FormBuilder, private invSvc: InventoryService, private router: Router) { }

  ngOnInit(): void {
    this.poForm = this.createPOForm();
  }

  createPOForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control('', [Validators.required, Validators.minLength(3)]),
      email: this.fb.control('', [Validators.required, Validators.email]),
      lineItems: this.liArr = this.fb.array([
          this.fb.group({
          prodName: this.fb.control('', [Validators.required, Validators.minLength(3)]),
          quantity: this.fb.control(1, [Validators.required, Validators.min(1)]),
          unitPrice: this.fb.control('', [Validators.required, Validators.min(0.1)]),
        })
      ], Validators.required)
    })
  }

  addLi() {
    this.liArr.push(
      this.fb.group({
        prodName: this.fb.control('', [Validators.required, Validators.minLength(3)]),
        quantity: this.fb.control(1, [Validators.required, Validators.min(1)]),
        unitPrice: this.fb.control('', [Validators.required, Validators.min(0.1)]),
      })
    );
  }
  
  deleteLi(index: number) {
    this.liArr.removeAt(index);
  }

  placeOrder() {
    const po = this.poForm.value as PO;
    console.info(po);
    this.invSvc.postPO(po)
      .then(result => {
        console.info(result)
        alert(`PO successfully submitted. Your Order Id is ${result.ordId}`)
      })
      .catch(error => alert(`Error: ${JSON.stringify(error)}`));
    this.poForm.reset();
    this.router.navigate(['/']);
  }
  
}
