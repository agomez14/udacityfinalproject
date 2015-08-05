/*!CK:2693369112!*//*1438197740,*/

if (self.CavalryLogger) { CavalryLogger.start_js(["pP6W5"]); }

__d("onCanPlayHTMLMediaElement",["EventListener","HTMLMediaElementReadyStates","invariant","setImmediate"],function(a,b,c,d,e,f,g,h,i,j){b.__markCompiled&&b.__markCompiled();function k(m){return m>=h.HAVE_FUTURE_DATA;}function l(m,n){i(m instanceof window.HTMLMediaElement);if(k(m.readyState))j(n);return g.listen(m,'canplay',n);}l.once=function(m,n){var o=l(m,function(){for(var p=[],q=0,r=arguments.length;q<r;q++)p.push(arguments[q]);o.remove();n.apply(this,p);}.bind(this));};e.exports=l;},null);