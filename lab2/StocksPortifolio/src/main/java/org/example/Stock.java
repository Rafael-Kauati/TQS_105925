package org.example;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Stock {
    private final String label;
    private final int quantity;
}
