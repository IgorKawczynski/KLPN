export enum Event_m {
    GOAL = 'GOAL',
    ASSIST = 'ASSIST',
    OWN_GOAL = 'OWN_GOAL',
    RED_CARD = 'RED_CARD',
    YELLOW_CARD = 'YELLOW_CARD'
  }
  
  export function mapToEnumValue(str: string): Event_m {
  
    switch(str) {
      case 'Goal':
        return Event_m.GOAL;
      case 'Asysta':
        return Event_m.ASSIST;
      case 'Bramka samobójcza':
        return Event_m.OWN_GOAL;
      case 'Czerwona kartka':
        return Event_m.RED_CARD;
      case 'Żółta kartka':
        return Event_m.YELLOW_CARD;
      default:
          break;
    }
  
    return Event_m.GOAL;
  }