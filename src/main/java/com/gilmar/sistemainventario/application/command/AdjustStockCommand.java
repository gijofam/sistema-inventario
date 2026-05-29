package com.gilmar.sistemainventario.application.command;

public record AdjustStockCommand(int delta, String reason) {
}
