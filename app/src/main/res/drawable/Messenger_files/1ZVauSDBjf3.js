/*!CK:2989944456!*//*1435677192,*/

if (self.CavalryLogger) { CavalryLogger.start_js(["axf0i"]); }

__d("XUIAmbientNUX.react",["HasLayerContextMixin","React","ReactLayer","ReactAbstractContextualDialog","XUIAmbientNUXTheme","XUIAmbientNUXBody.react"],function(a,b,c,d,e,f,g,h,i,j,k,l){b.__markCompiled&&b.__markCompiled();var m=h,n=m.PropTypes,o=300,p=380,q=i.createClass(j.createSpec({displayName:'XUIAmbientNUX',theme:k})),r=h.createClass({displayName:"XUIAmbientNUX",mixins:[g],propTypes:{alignment:n.oneOf(['left','center','right']),behaviors:n.object,context:n.object,contextRef:n.func,customwidth:n.number,offsetX:n.number,offsetY:n.number,onCloseButtonClick:n.func,position:n.oneOf(['above','below','left','right']),shown:n.bool,width:n.oneOf(['wide','normal','auto','custom'])},_getWidth:function(){switch(this.props.width){case 'wide':return p;case 'custom':return this.props.customwidth;case 'auto':return null;default:return o;}},render:function(){return (h.createElement(q,{alignment:this.props.alignment,autoFocus:false,behaviors:this.props.behaviors,context:this.getContextNode(),focusContextOnHide:false,offsetX:this.props.offsetX,offsetY:this.props.offsetY,position:this.props.position,shown:this.props.shown,width:this._getWidth(this.props)},h.createElement(l,{onCloseButtonClick:this.props.onCloseButtonClick},this.props.children)));}});e.exports=r;},null);