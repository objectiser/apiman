/*
 * 2012-3 Red Hat Inc. and/or its affiliates and other contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.overlord.apiman.model.policies;

import org.overlord.apiman.Message;
import org.overlord.apiman.Request;
import org.overlord.apiman.model.Policy;
import org.overlord.apiman.policy.PolicyContext;

public class IPWhiteListPolicy extends Policy {

	private java.util.List<String> _ipAddresses=new java.util.ArrayList<String>();
	
	/**
	 * This method returns the list of IP addresses.
	 * 
	 * @return The IP Addresses
	 */
	public java.util.List<String> getAddresses() {
		return (_ipAddresses);
	}
	
	/**
	 * This method sets the IP addresses.
	 * 
	 * @param addresses The IP addresses
	 */
	public void setAddresses(java.util.List<String> addresses) {
		_ipAddresses = addresses;
	}
	
    /**
     * {@inheritDoc}
     */
    public void apply(PolicyContext context, Message mesg) throws Exception {    

        if (mesg instanceof Request) {
            String ipaddr=((Request)mesg).getIPAddress();
            if (!getAddresses().contains(ipaddr)) {
                throw new Exception("Request's IP address '"+ipaddr+"' is not permitted to use this service: "+getAddresses());
            }
        } else {
            throw new Exception("IP White List validation can only performed against a request");
        }
    }

}
