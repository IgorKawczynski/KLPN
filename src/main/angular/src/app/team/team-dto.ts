import {PlayerStatsDTO} from "./team-detail/PlayerStatsDTO";

export class TeamDTO {
  teamId!: number;
  Name!: string;
  playerAndStatsDTOs!: PlayerStatsDTO[];
}
