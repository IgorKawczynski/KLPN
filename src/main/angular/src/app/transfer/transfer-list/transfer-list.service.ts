import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { SingleTransferResultDTO } from './single-transfer-result';

@Injectable({
  providedIn: 'root'
})
export class TransferListService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getSingleTransfers(): Observable<SingleTransferResultDTO[]> {
    return this.http.get<SingleTransferResultDTO[]>(`${this.apiServerUrl}/api/transfer/list`)
  }

}
