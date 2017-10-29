package org.w0a.extractor;

/*
 * class WebflowMap<T>
 * 
 * The purpose of this class is to provide a HashMap<String,T> 
 * container for the WebflowArchive complete with iterator. 
 * 
 * (c) Copyright 2017, ExPARX, Inc. 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.w0a.exceptions.InvalidTypeException;
import org.w0a.interfaces.SourceEntryInterface;
import org.w0a.types.SourceCSSFileEntry;
import org.w0a.types.SourceHTMLFileEntry;
import org.w0a.types.SourceIndexFileEntry;


@SuppressWarnings("serial")
public class SourceMap<T> extends HashMap<String, T> implements Iterable<Entry<String, T>> {

	/**
	 * iterator()
	 * 
	 * @return	an Iterator<Entry<String, T>> for use by WebflowMap<T>
	 * 			container objects.
	 */
	
	@Override
	public Iterator<java.util.Map.Entry<String, T>> iterator() {
		Iterator<Entry<String, T>> entries = this.entrySet().iterator();
		return entries;
	}
	
	public T first() {
		return this.get(this.keySet().toArray()[0]);
	}
	
	public SourceMap<SourceIndexFileEntry> getIndexes() {
		SourceMap<SourceIndexFileEntry> results = new SourceMap<SourceIndexFileEntry>();
		for (java.util.Map.Entry<String, T> entry : this) {
			if ( entry.getValue() instanceof SourceIndexFileEntry )
				results.put(entry.getKey(), (SourceIndexFileEntry)entry.getValue());
		}
		return results;
	}
	
	public SourceIndexFileEntry getIndex() {
		return getIndexes().first();
	}
	
	public SourceMap<SourceHTMLFileEntry> getHTMLPages() {
		SourceMap<SourceHTMLFileEntry> results = new SourceMap<SourceHTMLFileEntry>();
		for (java.util.Map.Entry<String, T> entry : this) {
			String key = entry.getKey();
			T value = entry.getValue();
			if ( value instanceof SourceHTMLFileEntry )
				results.put(key, (SourceHTMLFileEntry)value);
		}
		return results;
	}

	public SourceHTMLFileEntry getHTMLPage(String key) throws InvalidTypeException {
		T value = this.get(key);
		if ( value==null )
			return null;
		if ( value instanceof SourceHTMLFileEntry ) 
			return (SourceHTMLFileEntry)value;
		else 
			throw new InvalidTypeException();
	}
	
	public SourceMap<SourceCSSFileEntry> getCSSPages() {
		SourceMap<SourceCSSFileEntry> results = new SourceMap<SourceCSSFileEntry>();
		for (java.util.Map.Entry<String, T> entry : this) {
			String key = entry.getKey();
			T value = entry.getValue();
			if ( value instanceof SourceCSSFileEntry )
				results.put(key, (SourceCSSFileEntry)value);
		}
		return results;
	}
	
	public SourceCSSFileEntry getCSSPage(String key) {
		T value = this.get(key);
			if ( value instanceof SourceCSSFileEntry )
				return (SourceCSSFileEntry)value;
		return null;
	}
	
};
