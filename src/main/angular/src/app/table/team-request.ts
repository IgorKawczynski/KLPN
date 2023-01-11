export interface TeamRequest{

  id: number;
  name: string;
  numberOfMatches: number;
  wins: number;
  draws: number;
  loses: number;
  goalsFor: number;
  goalsAgainst: number;
  balance: number;
  points: number;
}
