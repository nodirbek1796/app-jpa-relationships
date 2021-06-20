package uz.fayz.appjparelationships.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UniversityDto {
    private String name;
    private String city;
    private String district;
    private String street;
}
