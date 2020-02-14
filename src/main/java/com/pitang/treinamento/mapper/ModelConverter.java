package com.pitang.treinamento.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;

public class ModelConverter {
	
	private static final String OUTPUT_FORMAT_FOR_DATE_HOUR = "dd/MM/yyyy";
	
	private ModelConverter() {
		throw new IllegalStateException("Utility class");
	}
	
	public static final Converter<Boolean, String> convertStatus = new AbstractConverter<Boolean, String>() {
		@Override
		protected String convert(Boolean source) {
			if(source) {
				return "Ativo";
			}else {
				return "Inativo";
			}
		}
	};
	
	public static final Converter<String, Boolean> convertStatusToBoolean = new AbstractConverter<String, Boolean>() {
		@Override
		protected Boolean convert(String source) {
			if(source.equals("Ativo")) {
				return true;
			}else {
				return false;
			}
		}
	};
	
	public static final Converter<Date, String> fromDateToString = new AbstractConverter<Date, String>() {
		@Override
		protected String convert(Date source) {
			if(source == null) {
				return null;
			}
			SimpleDateFormat df = new SimpleDateFormat(OUTPUT_FORMAT_FOR_DATE_HOUR);
			return df.format(source.getTime());
		}
	};
	
	public static final Converter<String, Date> fromStringToDate = new AbstractConverter<String, Date>() {
		@Override
		protected Date convert(String source) {
			SimpleDateFormat df = new SimpleDateFormat(OUTPUT_FORMAT_FOR_DATE_HOUR);

			df.setLenient(false);

			try {

				Date date = new Date();

				date = df.parse(source);

				return date;

			} catch (ParseException e) {

				System.out.println("ERRO na conversão -> " + e.getMessage());

				return null;
			}
		}
	};
}