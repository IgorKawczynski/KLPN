export enum Position {
  GOALKEEPER = 'GOALKEEPER',
  DEFENDER = 'DEFENDER',
  MIDFIELDER = 'MIDFIELDER',
  STRIKER = 'STRIKER'
}

export function mapToEnumValue(str: string): Position {

  switch(str) {
    case 'Napastnik':
      return Position.STRIKER;
    case 'Pomocnik':
      return Position.MIDFIELDER;
    case 'Obrońca':
      return Position.DEFENDER;
    case 'Bramkarz':
      return Position.GOALKEEPER;
    default:
        break;
  }

  return Position.MIDFIELDER;
}