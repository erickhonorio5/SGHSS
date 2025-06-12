package gestao.sghss.domain;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    private String zipCode;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;

    public String getFullAddress() {
        StringBuilder sb = new StringBuilder();
        sb.append(street).append(", ").append(number);
        if (complement != null && !complement.trim().isEmpty()) {
            sb.append(" - ").append(complement);
        }
        sb.append(", ").append(neighborhood)
                .append(", ").append(city)
                .append(" - ").append(state);
        return sb.toString();
    }
}
