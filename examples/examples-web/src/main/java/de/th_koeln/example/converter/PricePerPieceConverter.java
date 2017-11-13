package de.th_koeln.example.converter;

import java.math.BigDecimal;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import de.th_koeln.example.shoppingcart.attribute.Amount;
import de.th_koeln.example.shoppingcart.vo.PricePerPiece;

@FacesConverter(forClass = PricePerPiece.class)
public class PricePerPieceConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext aContext, UIComponent aComponent, String aValue) {
		return new PricePerPiece.Builder().forCurrency("EURO").withAmount(Amount.fromValue(new BigDecimal(aValue))).build();
	}

	@Override
	public String getAsString(FacesContext aContext, UIComponent aComponent, Object aValue) {
		PricePerPiece value = (PricePerPiece) aValue;
		return value.toString();
	}

}
