/*!CK:4239401553!*//*1438112564,*/

if (self.CavalryLogger) { CavalryLogger.start_js(["e5iBE"]); }

__d("NavigationTimingRecorder",["Banzai","BanzaiScuba","URI","performance"],function(a,b,c,d,e,f,g,h,i,j){b.__markCompiled&&b.__markCompiled();var k='navigation_timing';if(j.timing){var l=j.timing,m=false,n=new h(k,null,{addBrowserFields:true,addGeoFields:true,addPredictedGeographyFields:true,addMobileDeviceFields:true}),o=function(){var r={};return function(s,t){if(!(s in l||s in r)){r[s]=t;n.addInteger(s,t);}};},p=function(){if(m)return;var r=Object.keys(l);if(r.length===0)if(typeof l.toJSON==='function'){r=Object.keys(l.toJSON());}else r=Object.keys(Object.getPrototypeOf(l));r.forEach(function(w){if(l[w])n.addInteger(w,l[w]);});var s=o();if(a.MCustomTimingRecorder){var t=a.MCustomTimingRecorder.getMarks();t.forEach(function(w){s(w.name,w.date);});}if(j.getEntriesByType){var u=j.getEntriesByType("mark");u.forEach(function(w){s(w.name,Math.round(w.startTime)+j.timing.navigationStart);});}var v=new i(a.location.href);n.addNormal('protocol',v.getProtocol());n.addNormal('domain',v.getDomain());n.addNormal('port',v.getPort());n.addNormal('path',v.getPath());n.post();m=true;},q=function(){g.subscribe(g.SEND,p);};if(a.document.readyState==='complete'){q();}else a.addEventListener('load',q);}},null);
__d("NoscriptOverride",["Cookie","goURI"],function(a,b,c,d,e,f,g,h){b.__markCompiled&&b.__markCompiled();var i={redirectToJSPage:function(j){g.clear('noscript');h(j);}};e.exports=i;},null);