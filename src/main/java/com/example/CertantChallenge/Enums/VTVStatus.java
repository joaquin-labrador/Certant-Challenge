package com.example.CertantChallenge.Enums;

public enum VTVStatus {
    APTO, RECHAZADO, CONDICIONAL, VENCIDO;

    public static VTVStatus fromString(String status) {
        switch (status) {
            case "APTO":
                return APTO;
            case "RECHAZADO":
                return RECHAZADO;
            case "PENDIENTE":
                return CONDICIONAL;
            case "VENCIDO":
                return VENCIDO;

            default:
                return null;
        }
    }

    public static String toString(VTVStatus status) {
        switch (status) {
            case APTO:
                return "APTO";
            case RECHAZADO:
                return "RECHAZADO";
            case CONDICIONAL:
                return "CONDICIONAL";
            case VENCIDO:
                return "VENCIDO";
            default:
                return null;
        }
    }

    public static Boolean verifyStatus(String status) {
        switch (status) {
            case "APTO", "RECHAZADO", "CONDICIONAL", "VENCIDO":
                return true;
            default:
                return false;
        }
    }
}
