import { StudentPlayer } from "../student/student-player";

export class TeamCreateDto {
  name!: string;
  players!: StudentPlayer[];
  captainId?: number;
}