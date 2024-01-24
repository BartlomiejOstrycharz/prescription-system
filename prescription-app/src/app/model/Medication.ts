import { Purpose } from "./Purposes";

export interface Medication {
  medicationId: number;
  medicationName: string;
  purposeId: Purpose;
  volume: string;
  sideEffects: string;
}
