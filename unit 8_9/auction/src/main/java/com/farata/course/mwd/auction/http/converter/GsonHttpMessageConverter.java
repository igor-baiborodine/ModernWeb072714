package com.farata.course.mwd.auction.http.converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;


public class GsonHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

  public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
  private Gson gson;
  private Type type;
  private boolean prefixJson;

  public void setType(final Type type) {
    this.type = type;
  }

  public Type getType() {
    return type;
  }

  public GsonHttpMessageConverter() {
    this(new Gson());
  }

  public GsonHttpMessageConverter(boolean serializeNulls) {
    this(serializeNulls ? new GsonBuilder().serializeNulls().create() : new Gson());
  }

  public GsonHttpMessageConverter(Gson gson) {
    super(new MediaType("application", "json", DEFAULT_CHARSET));
    setGson(gson);
  }

  public void setGson(Gson gson) {
    Assert.notNull(gson, "'gson' must not be null");
    this.gson = gson;
  }

  @Override
  public boolean canRead(Class<?> clazz, MediaType mediaType) {
    return canRead(mediaType);
  }

  @Override
  public boolean canWrite(Class<?> clazz, MediaType mediaType) {
    return canWrite(mediaType);
  }

  @Override
  protected boolean supports(Class<?> clazz) {
    // should not be called, since we override canRead/Write instead
    throw new UnsupportedOperationException();
  }

  @Override
  protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage)
      throws IOException, HttpMessageNotReadableException {

    Reader json = new InputStreamReader(inputMessage.getBody(),
                                        getCharset(inputMessage.getHeaders()));

    try {
      return type != null ? gson.fromJson(json, type) : gson.fromJson(json, clazz);
    } catch (JsonParseException ex) {
      throw new HttpMessageNotReadableException("Could not read JSON: " + ex.getMessage(), ex);
    }
  }

  @Override
  protected void writeInternal(Object o, HttpOutputMessage outputMessage)
      throws IOException, HttpMessageNotWritableException {

    try (OutputStreamWriter writer = new OutputStreamWriter(
        outputMessage.getBody(), getCharset(outputMessage.getHeaders()))) {
      if (type != null) {
        this.gson.toJson(o, type, writer);
      } else {
        this.gson.toJson(o, writer);
      }
    } catch (JsonIOException ex) {
      throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
    }
  }

  private Charset getCharset(HttpHeaders headers) {
    if (headers != null && headers.getContentType() != null &&
        headers.getContentType().getCharSet() != null) {
      return headers.getContentType().getCharSet();
    }
    return DEFAULT_CHARSET;
  }

}
