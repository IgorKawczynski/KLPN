import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { SingleTransferDTO } from './single-transfer';

@Injectable({
  providedIn: 'root'
})
export class TransferService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public singleTransfer(singleTransferDTO: SingleTransferDTO): Observable<Object> {
    return this.http.post<SingleTransferDTO>(`${this.apiServerUrl}/api/transfer/single`, singleTransferDTO);
  }
  
}
