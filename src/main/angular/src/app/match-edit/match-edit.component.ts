import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-match-edit',
  templateUrl: './match-edit.component.html',
  styleUrls: ['./match-edit.component.scss']
})
export class MatchEditComponent implements OnInit {



  constructor(
    private _Activatedroute: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    // get dla statystyki meczowe dla id:
    // match.id = this._Activatedroute.snapshot.params['id'];

  }

}
