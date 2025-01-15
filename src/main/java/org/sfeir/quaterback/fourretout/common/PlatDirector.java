package org.sfeir.quaterback.fourretout.common;

import org.sfeir.quaterback.fourretout.dto.BurgerDto;
import org.sfeir.quaterback.fourretout.dto.PateDto;
import org.sfeir.quaterback.fourretout.dto.PizzaDto;
import org.sfeir.quaterback.fourretout.Plat;

public class PlatDirector {

    public static Plat burger(BurgerDto burgerDto) {
        return PlatBuilder.aPlat()
                          .withPain(burgerDto.pain())
                          .withFromage(burgerDto.fromage())
                          .withCrudite(burgerDto.crudite())
                          .withViande(burgerDto.viande())
                          .withAccompagnement1(burgerDto.accompagnement())
                          .build();
    }

    public Plat pizza(PizzaDto pizzaDto) {
        return PlatBuilder.aPlat()
                          .withPate(pizzaDto.pate())
                          .withFromage(pizzaDto.fromage())
                          .withSauce(pizzaDto.sauce())
                          .withAccompagnement1(pizzaDto.accompagnement1())
                          .withAccompagnement2(pizzaDto.accompagnement2())
                          .withAccompagnement3(pizzaDto.accompagnement3())
                          .build();
    }

    public Plat pate(PateDto pateDto) {
        return PlatBuilder.aPlat()
                          .withSauce(pateDto.sauce())
                          .withParmesan(pateDto.parmesan())
                          .withAccompagnement1(pateDto.accompagnement1())
                          .withAccompagnement2(pateDto.accompagnement2())
                          .build();
    }
}
