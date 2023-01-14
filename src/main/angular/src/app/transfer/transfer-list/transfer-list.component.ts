import { Component, OnInit } from '@angular/core';
import { SingleTransferResultDTO } from './single-transfer-result';
import { TransferListService } from './transfer-list.service';

@Component({
  selector: 'app-transfer-list',
  templateUrl: './transfer-list.component.html',
  styleUrls: ['./transfer-list.component.scss', '../../reservation-list/reservation-list.component.scss']
})
export class TransferListComponent implements OnInit {

  transfers: SingleTransferResultDTO[] = []; 

  constructor(
    private transferListService: TransferListService
  ) { }

  ngOnInit(): void {
    this.getTransfers();
  }

  public getTransfers(): void {
    this.transferListService.getSingleTransfers().subscribe((response => {
      this.transfers = response;
    }))
  }

}
