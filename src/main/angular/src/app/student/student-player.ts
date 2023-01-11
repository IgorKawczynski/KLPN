import { Position } from "./position-enum";

export class StudentPlayer{
  indexNumber!: number;
  position!: Position;
  isReferee!: boolean;

  constructor(indexNumber: number, position: Position, isReferee: boolean) {
    this.indexNumber = indexNumber;
    this.position = position;
    this.isReferee = isReferee;
  }
  
}